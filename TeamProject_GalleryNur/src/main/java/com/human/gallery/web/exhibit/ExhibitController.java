package com.human.gallery.web.exhibit;

import com.human.gallery.domain.exhibit.Exhibit;
import com.human.gallery.domain.exhibit.ExhibitRepository;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ExhibitController {

    private final ExhibitRepository exhibitRepository;
    @Value("${exhibitfile.dir}")
    private String fileDir;

    @Value("${exhibitfile.path}")
    private String filePath;
    @GetMapping("/exhibit")
    public String viewExhibit(@SessionAttribute(name = "user", required = false) Users user,
                              @ModelAttribute("exhibit") Exhibit exhibit,
                              Model model) {

        model.addAttribute("user", user);

        List<Exhibit> exhibitList = exhibitRepository.findAll();
        log.info("넘어온 값 = {}", exhibitList);
        model.addAttribute("exhibit", exhibitList);
        return "exhibit/exhibit";
    }

    @GetMapping("/exhibit/upload")
    public String viewUpload(@SessionAttribute(name = "user", required = false) Users user,
                             Model model) {
        model.addAttribute("user", user);
        return "exhibit/exhibitUpload";
    }
    @PostMapping("/exhibit/upload")
    public String doUpload(@ModelAttribute("exhibit") Exhibit exhi,
                           @RequestParam("file") MultipartFile file) throws IOException {

        log.info("넘어온 값 = {}", exhi);
        log.info("넘어온 파일 = {}", file.getOriginalFilename());

        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String fullPath = fileDir + uuid + file.getOriginalFilename();
            String artistPath = filePath + uuid + file.getOriginalFilename();
            log.info("파일 경로 fullPath = {}", fullPath);
            file.transferTo(new File(fullPath));
            exhibitRepository.addExhibit(exhi.getName(), artistPath, exhi.getArtist(), exhi.getStartDate(), exhi.getEndDate(), exhi.getInfo(), exhi.getPrice(), exhi.getTotal());
        }
        return "redirect:/exhibit";
    }
    @GetMapping("/exhibit/detail/{id}")
    public String viewDetail(@PathVariable("id") int id,
                             @SessionAttribute(name = "user", required = false) Users user,
                             Model model) {

        model.addAttribute("user", user);
        Exhibit exhibit = exhibitRepository.findById(id);
        log.info("넘어온 값 = {}", exhibit);
        model.addAttribute("exhibit", exhibit);
        return "exhibit/exhibitDetail";
    }
    @PostMapping("/exhibit/delete/{id}")
    @ResponseBody
    public String doDelete(@PathVariable("id") int id) {

        try {
            exhibitRepository.deleteById(id);
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }
    @GetMapping("/exhibit/modify/{id}")
    public String viewModify(@PathVariable("id") int id,
                             @SessionAttribute(name = "user", required = false) Users user,
                             Model model) {

        Exhibit exhibit = exhibitRepository.findById(id);
        model.addAttribute("exhibit", exhibit);
        model.addAttribute("user", user);
        return "exhibit/exhibitModify";
    }
    @PostMapping("/exhibit/modify/{id}")
    public String doModify(@PathVariable("id") int id,
                            @ModelAttribute("exhibit") Exhibit exhibit,
                            @RequestParam("file") MultipartFile file) throws IOException {
        log.info("넘어온 값 = {}", exhibit);
        log.info("수정 넘어온 파일 = {}", file);

        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String fullPath = fileDir + uuid + file.getOriginalFilename();
            String artistPath = filePath + uuid + file.getOriginalFilename();
            log.info("파일 경로 fullPath = {}", fullPath);
            exhibitRepository.updateById(exhibit.getName(), artistPath, exhibit.getArtist(), exhibit.getStartDate(),
                    exhibit.getEndDate(), exhibit.getInfo(), exhibit.getPrice(), exhibit.getTotal(), exhibit.getId());
            file.transferTo(new File(fullPath));
        }
        else {
            exhibitRepository.updateByIdWithoutImages(exhibit.getName(), exhibit.getArtist(), exhibit.getStartDate(),
                    exhibit.getEndDate(), exhibit.getInfo(), exhibit.getPrice(), exhibit.getTotal(), exhibit.getId());
        }
        return "redirect:/exhibit/detail/"+id;
    }
}
