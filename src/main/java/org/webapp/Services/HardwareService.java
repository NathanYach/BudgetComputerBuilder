package org.webapp.Services;

import org.webapp.Models.CPU;
import org.webapp.Models.GPU;
import org.webapp.Models.Hardware;
import org.webapp.Repositories.CPURepository;
import org.webapp.Repositories.GPURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HardwareService {
    private final CPURepository cpuRepository;
    private final GPURepository gpuRepository;

    @Autowired
    public HardwareService(CPURepository cpuRepository, GPURepository gpuRepository) {
        this.cpuRepository = cpuRepository;
        this.gpuRepository = gpuRepository;
    }

    public Iterable<CPU> getAllCPUs() {
        return cpuRepository.findAll();
    }

    public Iterable<GPU> getAllGPUs() {
        return gpuRepository.findAll();
    }

    public void saveGPU(GPU gpu){
        gpuRepository.save(gpu);
    }
    public void saveCPU(CPU cpu){
        cpuRepository.save(cpu);
    }

    public <T extends Hardware> Map<Integer, List<T>> getMap(Iterable<T> hardwareList){
        Map<Integer,List<T>> map = new LinkedHashMap<>();
        for (T hardware: hardwareList) {
            if(map.containsKey(hardware.getPrice())){
                map.get(hardware.getPrice()).add(hardware);
            }
            else {
                List<T> list = new ArrayList<>();
                list.add(hardware);
                map.put(hardware.getPrice(), list);
            }
        }

        return map;
    }
}