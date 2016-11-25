package tai.service;

import java.util.List;

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

import tai.dao.CommentaryDao;
import tai.domain.Commentary;
import tai.domain.Event;

@RestController
@RequestMapping("commentary")
public class CommentaryService {
	
	@Autowired
	CommentaryDao commentaryDao;
	
	@RequestMapping(value = "/{commentId}", method=RequestMethod.GET)
	public @ResponseBody Commentary getCommentary(@PathVariable String commentId){
		return commentaryDao.getCommentary(commentId);
	}
	
	@RequestMapping(value = "/commentlist/{eventId}", method=RequestMethod.GET)
	public @ResponseBody List<Commentary> getCommentaries(@PathVariable String eventId){
		return commentaryDao.getCommentList(eventId);
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void saveComment(@RequestBody Commentary comment){
		commentaryDao.insertData(comment);
	}
	
	@RequestMapping(value = "/update/{commentId}", method=RequestMethod.PUT)
	public void updateComment(@PathVariable String commentId, @RequestBody Commentary comment){
		commentaryDao.updateData(comment, commentId);
	}
	
	@RequestMapping(value = "/delete/{commentId}", method=RequestMethod.DELETE)
	public void deleteComment(@PathVariable String commentId){
		commentaryDao.deleteData(commentId);
	}

}
