package com.scope.project.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Others_REST {

    private static final Logger logger = Logger.getLogger(Others_REST.class);

    @GetMapping("/location/{pin}")
    public List<String> locationString(@PathVariable int pin)
    {
        String uri = "https://api.postalpincode.in/pincode/"+pin;
        RestTemplate restTemplate = new RestTemplate();

        Object[] obj = restTemplate.getForObject(uri, Object[].class);

        Map<String,Object> map = new HashMap<String,Object>();
        ObjectMapper mapper = new ObjectMapper();

        map = mapper.convertValue(obj[0], new TypeReference<Map<String, Object>>() {});
        List<Object> pfs = (List<Object>) map.get("PostOffice");

        List<String> res = new ArrayList<>();

        for(int i=0; i<pfs.size(); i++) {
            Map<String, String> pf = (Map<String, String>) pfs.get(i);

            pf.remove("BranchType");
            pf.remove("DeliveryStatus");

            StringBuilder locString = new StringBuilder();

            for (String val : pf.values()) {
                if (val != null) {
                    if (locString.isEmpty())
                        locString.append(val);
                    else
                        locString.append("," + val);
                }
            }

            logger.info(locString.toString());
            res.add(locString.toString());
        }

        return res;
    }
}
