package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.dto.binding.ContactBindingDto;
import baykov.daniel.fooddelivery.domain.entity.Contact;
import baykov.daniel.fooddelivery.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static baykov.daniel.fooddelivery.constant.Messages.DATE_TIME_NOW_PATTERN;

@Service
@AllArgsConstructor
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_NOW_PATTERN);

    public void saveContactMessage(ContactBindingDto contactBindingDto) {
        Contact contact = this.modelMapper.map(contactBindingDto, Contact.class);

        contact.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now()), dateTimeFormatter));
        this.contactRepository.saveAndFlush(contact);
    }
}
