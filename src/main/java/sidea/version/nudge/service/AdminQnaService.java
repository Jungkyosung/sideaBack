package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.AskDto;
import sidea.version.nudge.mapper.AdminQnaMapper;

import java.util.List;

@Service
public class AdminQnaService {

    @Autowired
    private AdminQnaMapper adminQnaMapper;

    //문의 목록 조회
    public List<AskDto> getQnaList() throws Exception{
        return adminQnaMapper.getQnaList();
    }


    //문의 상세 조회
    public AskDto getQnaDetail(long askIdx) throws Exception{
        return adminQnaMapper.getQnaDetail(askIdx);
    }

    //문의 답변 등록
    public int insertQna(AskDto askDto) throws Exception{
        return adminQnaMapper.insertQna(askDto);
    }

    //문의 답변 수정
    public int updateQna(AskDto askDto) throws Exception{
        return adminQnaMapper.updateQna(askDto);
    }

    //문의 답변 삭제
    public int deleteQna(AskDto askDto) throws Exception{
        return adminQnaMapper.deleteQna(askDto);
    }
}
