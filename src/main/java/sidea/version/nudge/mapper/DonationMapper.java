package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.DonationDto;
import sidea.version.nudge.dto.DonationInfoDto;
import sidea.version.nudge.dto.UserDonationDto;

import java.util.List;

@Mapper
public interface DonationMapper {

    //기부 리스트 조회
    public List<DonationDto> getDonationList() throws Exception;

    //기부 세부내역 조회
    public DonationInfoDto getDonationDetail(long donationIdx) throws Exception;

    //참여기부 리스트 조회
    public List<DonationInfoDto> getDonationListByUser(int userIdx) throws Exception;

    //기부참여
    public int donate(UserDonationDto userDonationDto) throws Exception;

}
