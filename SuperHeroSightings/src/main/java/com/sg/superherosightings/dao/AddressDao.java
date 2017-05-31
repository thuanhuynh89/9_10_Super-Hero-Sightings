/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface AddressDao {

    public void addAddress(Address address);

    public void deleteAddress(int addressId);

    public void updateAddress(Address address);
    
    public Address getAddressById(int id);

    public List<Address> getAllAddresses();

}
