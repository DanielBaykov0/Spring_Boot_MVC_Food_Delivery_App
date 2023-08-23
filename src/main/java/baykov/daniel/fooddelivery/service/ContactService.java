package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.dto.ContactBindingDto;
import baykov.daniel.fooddelivery.domain.dto.ContactModelDto;
import baykov.daniel.fooddelivery.domain.entity.Contact;
import baykov.daniel.fooddelivery.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;

    public ContactModelDto mapToModel(ContactBindingDto contactBindingDto) {
        return this.modelMapper.map(contactBindingDto, ContactModelDto.class);
    }

    public void saveContactMessage(ContactModelDto contactModelDto) {
        Contact contact = this.modelMapper.map(contactModelDto, Contact.class);

        DateTimeFormatter dateTimeFormatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
        contact.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now())));
        this.contactRepository.saveAndFlush(contact);
    }
}
