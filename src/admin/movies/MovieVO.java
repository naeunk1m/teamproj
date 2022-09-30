package admin.movies;

public class MovieVO {

	private Integer id;
	private String nm;
	private String story;
	private String person;
	private String genre;
	private Integer runtime;
	private String grade;
	private String rlsdate;
	private String picture;
	private String video;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRlsdate() {
		return rlsdate;
	}

	public void setRlsdate(String rlsdate) {
		this.rlsdate = rlsdate;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}


	@Override
	public String toString() {
		return "MovieVO [id=" + id + ", nm=" + nm + ", story=" + story + ", person=" + person + ", genre=" + genre
				+ ", runtime=" + runtime + ", grade=" + grade + ", rlsdate=" + rlsdate + ", picture=" + picture
				+ ", video=" + video + "]";
	}

}
