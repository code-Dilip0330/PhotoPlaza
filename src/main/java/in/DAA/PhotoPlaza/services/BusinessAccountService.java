package in.DAA.PhotoPlaza.services;

import in.DAA.PhotoPlaza.dto.BusinessAccountDto;
import in.DAA.PhotoPlaza.entites.User;

public interface BusinessAccountService {
    void createBusinessAccount(BusinessAccountDto businessAccountDto, User user);
    BusinessAccountDto getBusinessAccountDetails(User user);

    boolean hasBusinessAccount(User user);
}