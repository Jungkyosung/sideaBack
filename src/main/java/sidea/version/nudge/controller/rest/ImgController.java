package sidea.version.nudge.controller.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

@Slf4j
@RestController
@RequestMapping("/api/image")
public class ImgController {

    final String UPLOAD_DIR = "C:/java/upload/";

    @GetMapping("/{filename}")
    public void getImage(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        // Image를 읽어서 전달
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            response.setHeader("Content-Disposition", "inline;");

            byte[] buf = new byte[1024];
            fis = new FileInputStream(UPLOAD_DIR + filename);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(response.getOutputStream());
            int read;
            while ((read = bis.read(buf, 0, 1024)) != -1) {
                bos.write(buf, 0, read);
            }
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
}
