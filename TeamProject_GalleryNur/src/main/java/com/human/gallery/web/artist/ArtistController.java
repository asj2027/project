package com.human.gallery.web.artist;

import com.human.gallery.domain.artist.Artist;
import com.human.gallery.domain.artist.ArtistRepository;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/artist")
@Controller
@RequiredArgsConstructor
public class ArtistController {

	private final ArtistRepository artistRepository;

	@Value("${file.dir}")
	private String fileDir;

	@Value("${artistfile.path}")
	private String filePath;

	@GetMapping("")
	public String viewArtist(@SessionAttribute(name = "user", required = false) Users user,
							 Model model) {
		List<Artist> artistList = artistRepository.findAll();
		model.addAttribute("artist", artistList);
		model.addAttribute("user", user);
		return "artist/artist";
	}

	@RequestMapping("/upload")
	public String viewUpload(@SessionAttribute(name = "user", required = false) Users user,
							 Model model) {

		model.addAttribute("user", user);
		return "artist/uploadArtist";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String doUpload(@RequestParam("name") String name,
						   @RequestParam("career") String career,
						   @RequestParam("direction") String direction,
						   @RequestParam("file") MultipartFile file
	) throws IllegalStateException, IOException {

		log.info("name = {}", name);
		log.info("career = {}", career);
		log.info("direction = {}", direction);
		log.info("MultipartFile = {}", file);


		if (!file.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			String fullPath = fileDir + uuid + file.getOriginalFilename();
			String artistPath = filePath + uuid + file.getOriginalFilename();
			log.info("파일 경로 fullPath = {}", fullPath);
			file.transferTo(new File(fullPath));
			artistRepository.addArtist(artistPath, name, career, direction);
		}

		return "redirect:/artist";
	}

	@GetMapping("/detail/{artistid}")
	public String viewDetail(@PathVariable("artistid") int id,
							 @SessionAttribute(name = "user", required = false) Users user,
							 Model model) {

		Artist artist = artistRepository.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("artist", artist);
		return "artist/artistDetail";
	}

	@PostMapping("/delete/{artistid}")
	@ResponseBody
	public String delteArtist(@PathVariable("artistid") int id) {
		log.info("받아온 id 값 = {}", id);
		try {
			artistRepository.deleteById(id);
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}

	@GetMapping("/modify/{artistid}")
	public String viewModify(@SessionAttribute(name = "user", required = false) Users user,
							 Model model,
							 @PathVariable("artistid") int id) {
		Artist artist = artistRepository.findById(id);
		model.addAttribute("artist", artist);
		model.addAttribute("user", user);
		return "artist/modifyArtist";
	}
	@PostMapping("/modify/{artistid}")
	public String doModifyArtist(@PathVariable("artistid") int id,
								 @RequestParam("name") String name,
								 @RequestParam("career") String career,
								 @RequestParam("direction") String direction,
								 @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {

		if (!file.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			String fullPath = fileDir + uuid + file.getOriginalFilename();
			String artistPath = filePath + uuid + file.getOriginalFilename();
			log.info("파일 경로 fullPath = {}", fullPath);
			artistRepository.modifyById(artistPath, name, career, direction, id);
			file.transferTo(new File(fullPath));
		}
		else {
			artistRepository.modifyByIdWithOutImage(name, career, direction, id);
		}
		return "redirect:/artist/detail/"+id;
	}
	@PostMapping("/getArtist")
	@ResponseBody
	public String sendArtist() {
		log.info("sendArtsit 호출 여부");
		List<Artist> artistList = artistRepository.findAll();
		JSONArray jA = new JSONArray();
		for (int i = 0; i < artistList.size(); i++) {
			JSONObject jO = new JSONObject();
			Artist artist = artistList.get(i);
			jO.put("id", artist.getId());
			jO.put("name", artist.getName());
			jA.add(jO);
		}
		log.info("넘길 값 = {}", JSONArray.toJSONString(jA));
		return JSONArray.toJSONString(jA);
	}
}
