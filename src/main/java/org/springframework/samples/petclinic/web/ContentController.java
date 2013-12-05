package org.springframework.samples.petclinic.web;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeContentRepository;
import org.springframework.samples.petclinic.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 2:10
 */
@Controller
@RequestMapping("main/themes/content")
public class ContentController extends AbstractBaseController {


    @Autowired
    private ThemeContentRepository themeContentRepository;

    @Autowired
    private ContentService contentService;

    @Autowired
    private MainController mainController;

    @RequestMapping("/{themeId}/finish/{contentId}")
    public String finish(@PathVariable Integer themeId,@PathVariable Integer contentId, Map<String,Object> model){
        contentService.handleFinishReading(contentId);
        return mainController.getThemeContents(model, themeId);
    }

    @RequestMapping("/{id}/play.mp3")
    @ResponseBody
    public FileSystemResource getWav(@PathVariable Integer id){
        URL url;
        File file = null;
        try {
            String text = themeContentRepository.findOne(id).getContentBody();
            url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=en&q="+URLEncoder.encode(text , "UTF-8"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("User-Agent", USER_AGENT);
            conn.connect();
            InputStream audioSrc = conn.getInputStream();
            DataInputStream read = new DataInputStream(audioSrc);
            file = stream2file(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileSystemResource(file);
    }

    private static final String PREFIX = "stream2file";
    private static final String SUFFIX = ".tmp";

    private static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }
}
