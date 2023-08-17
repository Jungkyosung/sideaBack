package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.DonationDto;

import java.util.List;

@Mapper
public interface DonationMapper {

    //기부 리스트 조회
    public List<DonationDto> getDonationList() throws Exception;

    //기부 세부내역 조회
    public DonationDto getDonationDetail(String donationIdx) throws Exception;


}
