import java.util.List;


public class YAHOONewsService implements NewsService {

	@Override
	public List<NewsGroup> fetchNews() {
		// TODO Auto-generated method stub
		return null;
	}
//        
//        private static final Logger logger = Logger.getLogger(YAHOONewsService.class.getName());
//        
//        private Map<String,String> feeds;
//        
//        public YAHOONewsService() {
//                feeds = new HashMap<String, String>();
//                feeds.put("Top Stories", "http://rss.news.yahoo.com/rss/topstories");
//                feeds.put("World", "http://rss.news.yahoo.com/rss/world");
//                feeds.put("Technology", "http://rss.news.yahoo.com/rss/tech");
//                feeds.put("Sports", "http://rss.news.yahoo.com/rss/sports");
//                feeds.put("Entertainment", "http://rss.news.yahoo.com/rss/entertainment");
//                feeds.put("Business", "http://rss.news.yahoo.com/rss/business");
//                feeds.put("Politics", "http://rss.news.yahoo.com/rss/politics");
//        }
//
//        public List<NewsGroup> fetchNews() {
//                List<NewsGroup> news = new ArrayList<NewsGroup>();
//                try {
//                        for(String key : feeds.keySet()) {
//                                URL feedSource = new URL(feeds.get(key));
//                                SyndFeedInput input = new SyndFeedInput();
//                                SyndFeed feed = input.build(new XmlReader(feedSource));
//                                List<NewsEntry> entries = new ArrayList<NewsEntry>();
//                                
//                                int i = 0;
//                                for(Object f : feed.getEntries()) {
//                                        SyndEntry entry = (SyndEntry) f;
//                    String title = entry.getTitle();
//                    title = title.length() <= 25 ? title : title.substring(0, 25);
//                                        entries.add(new NewsEntry(i, title + "...", entry.getDescription().getValue()));
//                                        i++;
//                                }
//                                
//                                news.add(new NewsGroup(key, entries)); 
//                        }
//                
//                } catch(Exception exception) {
//                        logger.log(Level.SEVERE, "Error in retrieving news from YAHOO: {0}", exception.getMessage());
//                }
//                
//                return news;
//        }

}
