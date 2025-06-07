package jp.co.volante.sample;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.volante.sample.dto.SampleDto;

@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    public List<SampleDto> getSample() {
        return sampleRepository.findAll().stream().limit(10).map(this::toDto).collect(Collectors.toList());
    }

    public SampleDto createSample(SampleDto dto) {
        Sample sample = new Sample();
        sample.setName(dto.getName());
        sample.setDescription(dto.getDescription());
        Sample saved = sampleRepository.save(sample);
        return toDto(saved);
    }

    private SampleDto toDto(Sample sample) {
        SampleDto dto = new SampleDto();
        dto.setId(sample.getId());
        dto.setName(sample.getName());
        dto.setDescription(sample.getDescription());
        return dto;
    }
}
