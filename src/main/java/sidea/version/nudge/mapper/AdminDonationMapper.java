package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.DonationInfoDto;

import java.util.List;

@Mapper
public interface AdminDonationMapper {


    //캠페인 목록 조회
    public List<DonationInfoDto> getDonationList() throws Exception;

    //캠페인 세부내역 조회
    public DonationInfoDto getDonationDetail(String donationIdx) throws Exception;

    //캠페인 등록
    public int insertDonation(DonationInfoDto donationInfoDto) throws Exception;
}
