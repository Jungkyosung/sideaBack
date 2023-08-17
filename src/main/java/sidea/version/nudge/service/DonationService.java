package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.DonationDto;
import sidea.version.nudge.mapper.DonationMapper;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationMapper donationMapper;

    //기부 리스트 조회
    public List<DonationDto> getDonationList() throws Exception{
        return donationMapper.getDonationList();
    }

    //기부 세부내역 조회
    public DonationDto getDonationDetail(String donationIdx) throws Exception{
        return donationMapper.getDonationDetail(donationIdx);
    }

}
