package tai.service;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tai.dao.EventDao;
import tai.domain.Event;

@RestController
@RequestMapping("event")
public class EventService {
	
	@Autowired
	EventDao eventDao;
	
	private String TOPIC_NAME = "test";
	
	@RequestMapping(value = "/{eventId}", method=RequestMethod.GET)
	public @ResponseBody Event getEvent(@PathVariable String eventId){
		return eventDao.getEvent(eventId);
	}
	
	@RequestMapping(value = "/eventlist", method=RequestMethod.GET)
	public @ResponseBody List<Event> getEvents(){
		return eventDao.getEventList();
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void saveEvent(@RequestBody Event event){
		
		Producer<String, String> producer = getProducer();
		producer.send(new ProducerRecord<String, String>(TOPIC_NAME, Integer.toString(event.getEventID()), event.toString()));
		producer.close();
		
		eventDao.insertData(event);
	}
	
	@RequestMapping(value = "/update/{eventId}", method=RequestMethod.PUT)
	public void updateEvent(@PathVariable String eventId, @RequestBody Event event){
		eventDao.updateData(event, eventId);
	}

	
	private Producer<String, String> getProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);
		return producer;
	}
}
