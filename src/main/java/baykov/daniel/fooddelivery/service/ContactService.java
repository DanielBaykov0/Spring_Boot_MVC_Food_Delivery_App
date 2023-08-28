package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.dto.binding.ContactBindingDto;
import baykov.daniel.fooddelivery.domain.entity.Contact;
import baykov.daniel.fooddelivery.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static baykov.daniel.fooddelivery.constants.Messages.DATE_TIME_NOW_PATTERN;

@Service
@AllArgsConstructor
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;

    public void saveContactMessage(ContactBindingDto contactBindingDto) {
        Contact contact = this.modelMapper.map(contactBindingDto, Contact.class);

        DateTimeFormatter dateTimeFormatter
                = DateTimeFormatter.ofPattern(DATE_TIME_NOW_PATTERN);
        contact.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now())));
        this.contactRepository.saveAndFlush(contact);
    }
}
