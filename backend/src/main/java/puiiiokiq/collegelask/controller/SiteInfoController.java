package puiiiokiq.collegelask.controller;

import org.springframework.web.bind.annotation.*;
import puiiiokiq.collegelask.model.SiteInfo;
import puiiiokiq.collegelask.repository.SiteInfoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/home-info")
public class SiteInfoController {

    private final SiteInfoRepository repository;

    public SiteInfoController(SiteInfoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SiteInfo> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public SiteInfo create(@RequestBody SiteInfo info) {
        return repository.save(info);
    }
}