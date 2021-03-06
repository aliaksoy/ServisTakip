

import java.util.ArrayList;
import java.util.List;

public class NewsController {

        private List<NewsGroup> groups = new ArrayList<NewsGroup>();
       
        private NewsService newsService = new YAHOONewsService();
       
        private NewsEntry selectedEntry;

    private NewsGroup selectedGroup;

        public NewsController() {
                groups = this.newsService.fetchNews();
        }

        public List<NewsGroup> getGroups() {
                return groups;
        }

    public NewsEntry getSelectedEntry() {
        return selectedEntry;
    }
    public void setSelectedEntry(NewsEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }


    public NewsGroup getSelectedGroup() {
        return selectedGroup;
    }
    public void setSelectedGroup(NewsGroup selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}

