package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.service.UserService;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //회원정보 조회
    @GetMapping("/userinfo")
    public ResponseEntity<Object> getUser(@RequestParam(value = "userEmail") String userEmail) throws Exception{

        UserDto user = userService.getUser(userEmail);

        if( user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("유저정보가 없음");
        }
    }


    //회원정보 변경
    @PutMapping("/user")
    public ResponseEntity<Object> userLeave(@RequestPart(value = "prfImg") MultipartFile prfImg, @RequestPart(value = "data") UserDto userDto) throws Exception{

        log.info("prfImg={}",prfImg);
        log.info("userDto={}",userDto);
        Map<String, String> imgInfo = saveFiles(prfImg);
        String imgName = imgInfo.get("savedFileName");
        userDto.setUserImage(imgName);

        int leftCnt = userService.modifyUser(userDto);

        if( leftCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(leftCnt);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(leftCnt);
        }
    }

    //회원탈퇴
    @PutMapping("/user/delete")
    public ResponseEntity<Object> deleteUser(@RequestBody UserDto userDto) throws Exception{

        log.info("유저이메일={}",userDto.getUserEmail());
        log.info("유저비밀번호={}",userDto.getUserPw());

        int deletedCnt = userService.deleteUser(userDto);

        if( deletedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(deletedCnt);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(deletedCnt);
        }
    }

    //닉네임 중복 확인
    @GetMapping("/usernickname/duplicate")
    public ResponseEntity<Integer> nicknameDuplCheck(@RequestParam(value = "usernickname") String userNickname) throws Exception{

        UserDto selectedUser = userService.nicknameDuplCheck(userNickname);

        if( selectedUser == null){
            return ResponseEntity.status(HttpStatus.OK).body(0);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
    }

    //비밀번호 확인
    @GetMapping("/userpw")
    public ResponseEntity<String> selectUserPw(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "userPw") String userPw) throws Exception{

        log.info("입력 받은 비밀번호 = {}",userPw);
        boolean selectedUser = userService.selectUserPw(userEmail, userPw);

        if( selectedUser ){
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 일치");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 불일치");
        }
    }

    final String UPLOAD_DIR = "C:/java/upload/";

    public Map<String, String> saveFiles(MultipartFile files) {
        Map<String, String> result = new HashMap<>();

        if (files != null) {
            String originFileName = files.getOriginalFilename();
            System.out.println(">>>>>>>>>>>>>>>>>>" + originFileName);
            String savedFileName = System.currentTimeMillis() + originFileName;

            try {
                File f = new File(UPLOAD_DIR + savedFileName);
                files.transferTo(f);

                result.put("originalFileName", originFileName);
                result.put("savedFileName", savedFileName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
