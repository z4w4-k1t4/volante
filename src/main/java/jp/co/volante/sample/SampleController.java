package jp.co.volante.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.volante.sample.dto.SampleDto;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @GetMapping
    public List<SampleDto> getSamples() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = (String) authentication.getPrincipal();
        return sampleService.getSample();
    }

    @PostMapping
    public SampleDto createSample(@RequestBody SampleDto sampleDto) {
        return sampleService.createSample(sampleDto);
    }
}