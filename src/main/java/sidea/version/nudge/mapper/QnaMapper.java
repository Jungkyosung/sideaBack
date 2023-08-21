package sidea.version.nudge.mapper;


import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.AskDto;

import java.util.List;

@Mapper
public interface QnaMapper {

    //회원 QNA 목록 조회
    public List<AskDto> getQnaList(long userIdx) throws Exception;

    //QNA 상세보기(조회)
    public AskDto getQnaDetail(long askIdx, long userIdx) throws Exception;

    //QNA 등록
    public int insertQna(AskDto askDto) throws Exception;

    //QNA 수정
    public int updateQna(AskDto askDto) throws Exception;

    //QNA 삭제
    public int deleteQna(AskDto askDto) throws Exception;


}
