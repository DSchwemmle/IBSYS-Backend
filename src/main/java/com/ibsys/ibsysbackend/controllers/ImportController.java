package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.entities.ImportData;
import com.ibsys.ibsysbackend.services.ImportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.IMPORT)
public class ImportController {

    private final ImportService importService;

    @PostMapping
    void importData(@RequestBody final ImportData importData) {
        importService.processImport(importData);
    }
}
