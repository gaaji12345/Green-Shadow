package com.gaajiFarm.GaajiFarm.service;/*  gaajiCode
    99
    06/11/2024
    */

import com.gaajiFarm.GaajiFarm.dtos.EqDTO;
import com.gaajiFarm.GaajiFarm.util.enitity.Equpment;
import com.gaajiFarm.GaajiFarm.util.enitity.Field;
import com.gaajiFarm.GaajiFarm.util.enitity.Staff;
import com.gaajiFarm.GaajiFarm.util.enums.EqupmentTypes;
import com.gaajiFarm.GaajiFarm.util.enums.Status;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;

public interface EqService {

    public EqDTO saveEq(EqDTO eDTO) ;



    public void updateEq(EqDTO c) ;


    public void deleteEq(String sid);



    public String genarateNextEcode() ;

}
