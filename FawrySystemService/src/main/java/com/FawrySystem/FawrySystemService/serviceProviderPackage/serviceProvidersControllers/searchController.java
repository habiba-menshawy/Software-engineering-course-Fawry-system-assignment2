package com.FawrySystem.FawrySystemService.serviceProviderPackage.serviceProvidersControllers;

import com.FawrySystem.FawrySystemService.serviceProviderPackage.serviceProvidersBSL.BasicBSL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@RequestMapping("searchService")
public class searchController {

    BasicBSL basicBSL = new BasicBSL();

    @GetMapping ("/{query}")
    public ResponseEntity<Object> search(@PathVariable String query) {
        return new ResponseEntity<>(basicBSL.searchSP(query), HttpStatus.OK);
    }

}
