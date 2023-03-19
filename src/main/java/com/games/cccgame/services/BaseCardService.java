package com.games.cccgame.services;

import com.games.cccgame.commands.CreateBaseCardCommand;;
import com.games.cccgame.commands.UpdateBaseCardCommand;
import com.games.cccgame.dtos.BaseCardDTO;
import com.games.cccgame.models.BaseCard;
import com.games.cccgame.repositories.BaseCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BaseCardService {

    private BaseCardRepository baseCardRepository;

    private ModelMapper modelMapper;

    public BaseCardDTO getBaseCardById(String id) {
        BaseCard baseCard = baseCardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("BaseCard dont exists with this id: " + id));
        baseCardRepository.save(baseCard);

        return modelMapper.map(baseCard, BaseCardDTO.class);
    }

    public BaseCardDTO createBaseCard(CreateBaseCardCommand command) {
        BaseCard baseCard = modelMapper.map(command, BaseCard.class);
        baseCardRepository.save(baseCard);

        return modelMapper.map(baseCard, BaseCardDTO.class);
    }

    public BaseCardDTO updateBaseCard(UpdateBaseCardCommand command) {
        BaseCard baseCard = modelMapper.map(command, BaseCard.class);
        baseCardRepository.save(baseCard);

        return null;
    }

    public void deleteBaseCard(String id) {
        baseCardRepository.deleteById(id);
        log.info(id + " baseCard deleted successfully");
    }
}
