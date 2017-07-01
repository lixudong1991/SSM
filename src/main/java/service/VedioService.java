package service;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface VedioService
{
	Map<String,File> getVideos();
	List<String> getItems();
}
