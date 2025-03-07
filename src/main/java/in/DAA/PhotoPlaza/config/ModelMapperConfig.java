package in.DAA.PhotoPlaza.config;

import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.entites.ProfileDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping for ProfileDetailsDto to ProfileDetails
        modelMapper.addMappings(new PropertyMap<ProfileDetailsDto, ProfileDetails>() {
            @Override
            protected void configure() {
                // Map userId to user.id
                map().getUser().setId(source.getUserId());

                // Ignore other ambiguous properties
                skip().getUser().setUserName(null);
                skip().getUser().setFirstName(null);
                skip().getUser().setLastName(null);
            }
        });

        return modelMapper;
    }
}
