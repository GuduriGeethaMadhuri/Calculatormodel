package com.example.demo.controller;




import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CoreModelCalculatorController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    @ResponseBody
    public Map<String, Object> calculateCoreModel(@RequestBody Map<String, String> request) {
        String coreModel = request.get("coreModel");

        // Logic to calculate the various parameters based on the coreModel
        Map<String, Object> result = new HashMap<>();
        result.put("coreFactors", formatDecimal(calculateCoreFactors(coreModel)));
        result.put("effectiveVolume", formatDecimal(calculateEffectiveVolume(coreModel)));
        result.put("effectiveLength", formatDecimal(calculateEffectiveLength(coreModel)));
        result.put("effectiveArea", formatDecimal(calculateEffectiveArea(coreModel)));
        result.put("minimumArea", formatDecimal(calculateMinimumArea(coreModel)));

        return result;
    }

   
    private double[] parseCoreModel(String coreModel) {
        
        String[] parts = coreModel.substring(2).split("/"); 
        double width = Double.parseDouble(parts[0]); 
        double height = Double.parseDouble(parts[1]); 
        double innerWidth = Double.parseDouble(parts[2]); 
        return new double[]{width, height, innerWidth};
    }

   
    private double calculateCoreFactors(String coreModel) {
        double[] dimensions = parseCoreModel(coreModel);
        return dimensions[0] * 0.1; 
    }

    
    private double calculateEffectiveVolume(String coreModel) {
        double[] dimensions = parseCoreModel(coreModel);
        return dimensions[0] * dimensions[1] * dimensions[2] * 0.01; 
    }

   
    private double calculateEffectiveLength(String coreModel) {
        double[] dimensions = parseCoreModel(coreModel);
        return dimensions[1] * 0.2; 
    }


    private double calculateEffectiveArea(String coreModel) {
        double[] dimensions = parseCoreModel(coreModel);
        return dimensions[0] * dimensions[1] * 0.05; 
    }

    
    private double calculateMinimumArea(String coreModel) {
        double[] dimensions = parseCoreModel(coreModel);
        return dimensions[2] * 0.1; 
    }

    
    private String formatDecimal(double value) {
        return String.format("%.1f", value);
    }
}





