package com.usa.ciclo3.reto3ciclo3master.service;

import com.usa.ciclo3.reto3ciclo3master.entities.Message;
import com.usa.ciclo3.reto3ciclo3master.entities.Product;
import com.usa.ciclo3.reto3ciclo3master.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage (int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message p){
        if(p.getId()==null){
            return messageRepository.save(p);
        }else{
            Optional<Message> e = messageRepository.getMessage(p.getId());
            if (e.isPresent()){
                return p;
            }else{
                return messageRepository.save(p);
            }
        }
    }

    public Message update(Message p){
        if (p.getId()!=null){
            Optional<Message> q= messageRepository.getMessage(p.getId());
            if (q.isPresent()){
                if(p.getMessageText()!=null){
                    q.get().setMessageText(p.getMessageText());
                }
                if(p.getCabin()!=null){
                    q.get().setCabin(p.getCabin());
                }
                if(p.getClient()!=null){
                    q.get().setClient(p.getClient());
                }
                messageRepository.save(q.get());
                return q.get();
            }else{
                return p;
            }
        }else{
            return p;
        }
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Message> p = messageRepository.getMessage(id);
        if (p.isPresent()) {
            messageRepository.delete(p.get());
            flag = true;
        }
        return flag;
    }

}
