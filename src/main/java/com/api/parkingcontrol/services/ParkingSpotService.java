package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingSpotService {
    
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public ResponseEntity<Object> save(ParkingSpotModel parkingSpotModel){

        
        /*  Validações 
         * A vaga estar sendo ocupada?  
         * O carro com a placa já esixte um registro?
         * O bloco do apartamento específco já existe um registro?
         */


        //  Verificar se a placa do carro já existe
        if(parkingSpotRepository.existsByLicensePlateCar(parkingSpotModel.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Lincese Plate car is already in use");
        }

        //Verificar se a vaga já está em uso
        if(parkingSpotRepository.existsByparkingSpotNumber(parkingSpotModel.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use");
        }

        //Verificar se o apartamento e o block possui um registro
        if(parkingSpotRepository.existsByApartmentAndBlock(parkingSpotModel.getApartment(), parkingSpotModel.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already registered for this apartment/block");
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotRepository.save(parkingSpotModel));
    }


    public List<ParkingSpotModel> findAll(){
        return parkingSpotRepository.findAll();
    }


    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }


}
