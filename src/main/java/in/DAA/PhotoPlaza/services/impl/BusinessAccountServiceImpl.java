package in.DAA.PhotoPlaza.services.impl;

import in.DAA.PhotoPlaza.dto.BusinessAccountDto;
import in.DAA.PhotoPlaza.entites.BusinessAccount;
import in.DAA.PhotoPlaza.entites.Role;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.repositories.BusinessAccountRepository;
import in.DAA.PhotoPlaza.repositories.RoleRepository;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.services.BusinessAccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BusinessAccountServiceImpl implements BusinessAccountService {

    private  BusinessAccountRepository businessAccountRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public void createBusinessAccount(BusinessAccountDto businessAccountDto, User user) {
        BusinessAccount businessAccount = modelMapper.map(businessAccountDto,BusinessAccount.class);
        Optional<BusinessAccount> existingBusinessAccount = businessAccountRepository.findByUser(user);

//        Validate userId is not null
        if (businessAccountDto.getUserId()==null){
            throw new IllegalArgumentException("User Id must be not null");
        }
//        Ensure must be assigned
        user = userRepository.findById(businessAccountDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not found"));

        // Fetch the SELLER role
        Role sellerRole = roleRepository.findByName("SELLER")
                .orElseThrow(() -> new RuntimeException("SELLER role not found"));


        // Assign SELLER role if not already present
        if (user.getRoles().stream().noneMatch(role -> role.getName().equals("SELLER"))) {
            user.getRoles().add(sellerRole);
            userRepository.save(user); // Save user with updated roles
        }

        if (existingBusinessAccount.isPresent()){
//            Update existing businessAccount
            BusinessAccount account = existingBusinessAccount.get();
            account.setBusinessName(businessAccount.getBusinessName());
            account.setBusinessType(businessAccount.getBusinessType());
            account.setWebsiteUrl(businessAccount.getWebsiteUrl());
            businessAccountRepository.save(account);
        } else {
//            Create new Business Account
            businessAccount.setUser(user);
            businessAccountRepository.save(businessAccount);
        }
    }

    @Override
    public BusinessAccountDto getBusinessAccountDetails(User user) {
        BusinessAccount account = businessAccountRepository.findByUserId(user.getId());
        // Convert entity to DTO and return
        return null;
    }
    @Override
    public boolean hasBusinessAccount(User user) {
        return businessAccountRepository.findByUser(user).isPresent();
    }
}

//private String saveLogo(MultipartFile file) {
//        try {
//            Path uploadPath = Paths.get(UPLOAD_DIR);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            Files.copy(file.getInputStream(), uploadPath.resolve(fileName));
//            return fileName;
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to store logo", e);
//        }
//    }
//@Override
//public void createBusinessAccount(BusinessAccountDto dto, User user) {
//    BusinessAccount account = new BusinessAccount();
//    account.setFullName(dto.getFullName());
//    account.setBusinessEmail(dto.getBusinessEmail());
//    account.setBusinessContact(dto.getBusinessContact());
//    account.setWebsiteUrl(dto.getWebsiteUrl());
//    account.setBusinessType(dto.getBusinessType());
//    account.setBio(dto.getBio());
////        account.setUser(user);
//
//
//    if (!dto.getLogo().isEmpty()) {
//        String fileName = saveLogo(dto.getLogo());
//        account.setLogoPath(fileName);
//    }
//
//    businessAccountRepository.save(account);
//}