package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.DonationInfoDto;
import sidea.version.nudge.mapper.AdminDonationMapper;

import java.util.List;

@Service
public class AdminDonationService {

    @Autowired
    private AdminDonationMapper adminDonationMapper;

    //캠페인 목록 조회
    public List<DonationInfoDto> getDonationList() throws Exception{
        return adminDonationMapper.getDonationList();
    }

    //캠페인 세부내역 조회
    public DonationInfoDto getDonationDetail(long donationIdx) throws Exception{
        return adminDonationMapper.getDonationDetail(donationIdx);
    }

    //캠페인 등록
    public int insertDonation(DonationInfoDto donationInfoDto) throws Exception{
        return adminDonationMapper.insertDonation(donationInfoDto);
    }

    //캠페인 수정
    public int updateDonation(DonationInfoDto donationInfoDto) throws Exception{
        return adminDonationMapper.updateDonation(donationInfoDto);
    }

    //캠페인 삭제
    public int deleteDonation(long donationIdx) throws Exception{
        return adminDonationMapper.deleteDonation(donationIdx);
    }


}
