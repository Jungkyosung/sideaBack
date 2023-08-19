package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.AskDto;

import java.util.List;

@Mapper
public interface AdminQnaMapper {

    //문의 목록 조회
    public List<AskDto> getQnaList() throws Exception;

    //문의 상세 조회
    public AskDto getQnaDetail(long askIdx) throws Exception;

    //문의 답변 등록
    public int insertQna(AskDto askDto) throws Exception;

    //문의 답변 수정
    public int updateQna(AskDto askDto) throws Exception;

    //문의 답변 삭제
    public int deleteQna(AskDto askDto) throws Exception;

}
