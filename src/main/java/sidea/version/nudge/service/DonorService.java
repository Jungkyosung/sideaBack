package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.DonorDto;
import sidea.version.nudge.mapper.DonorMapper;

import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorMapper donorMapper;

    public List<DonorDto> getDonorList() throws Exception{
        return donorMapper.getDonorList();
    }

}
