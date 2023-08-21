package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.AskDto;
import sidea.version.nudge.mapper.QnaMapper;

import java.util.List;

@Service
public class QnaService {

    @Autowired
    private QnaMapper qnaMapper;

    //회원 QNA 목록 조회
    public List<AskDto> getQnaList(long userIdx) throws Exception{
        return qnaMapper.getQnaList(userIdx);
    }

    //QNA 상세보기(조회)
    public AskDto getQnaDetail(long askIdx, long userIdx) throws Exception{
        return qnaMapper.getQnaDetail(askIdx, userIdx);
    }

    //QNA 작성
    public int insertQna(AskDto askDto) throws Exception{
        return qnaMapper.insertQna(askDto);
    }

    //QNA 수정
    public int updateQna(AskDto askDto) throws Exception{
        return qnaMapper.updateQna(askDto);
    }

    //QNA 삭제
    public int deleteQna(AskDto askDto) throws Exception{
        return qnaMapper.deleteQna(askDto);
    }
}
