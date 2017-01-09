package tai.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import tai.domain.Event;
import tai.jdbc.EventRowMapper;

public class EventDaoImpl implements EventDao {

	@Autowired
	DataSource dataSource;

	public void insertData(Event event) {

		String sql = "INSERT INTO Event "
				+ "(title, start_date, content) VALUES (?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		jdbcTemplate.update(
				sql,
				new Object[] { event.getTitle(), dt.format(event.getStartDate()), event.getContent()});

	}

	public List<Event> getEventList() {
		List<Event> eventList = new ArrayList<Event>();

		String sql = "select e.event_id, e.title, e.start_date, e.content, (select count(*) from Commentary as c where e.event_id=c.event_id) "
				+ "from Event as e ;";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		eventList = jdbcTemplate.query(sql, new EventRowMapper());
		return eventList;
	}

	@Override
	public void deleteData(String id) {
		String sql = "delete from Event where event_id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);

	}

	@Override
	public void updateData(Event event, String id) {
		if(event.getTitle().compareTo("")!=0) {
			String sql = "UPDATE Event set title = ? WHERE event_id="+id;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(
					sql,
					new Object[] {event.getTitle()});
		}
		if(event.getContent().compareTo("")!=0) {
			String sql = "UPDATE Event set content = ? WHERE event_id="+id;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(
					sql,
					new Object[] {event.getContent()});
		}

		if(event.getStartDate()!=null) {
			try {
				String sql = "UPDATE Event set start_date = ? WHERE event_id="+id;
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
				
				jdbcTemplate.update(
					sql,
					new Object[] { dt.format(event.getStartDate()) });
			} catch(Exception e) {
				return;
			}
		}

	}

	@Override
	public Event getEvent(String id) {
		List<Event> eventList = new ArrayList<Event>();
		//String sql = "select * from Event where event_id= " + id;
		String sql = "select e.event_id, e.title, e.start_date, e.content, (select count(*) from Commentary as c where e.event_id=c.event_id) "
				+ "from Event as e "
				+ "where e.event_id="+id+";";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		eventList = jdbcTemplate.query(sql, new EventRowMapper());
		return eventList.get(0);
	}

}
