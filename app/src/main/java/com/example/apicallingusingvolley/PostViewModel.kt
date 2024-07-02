import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicallingusingvolley.Post
import com.example.apicallingusingvolley.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val posts: LiveData<List<Post>> = repository.posts

    fun fetchPosts(context: Context) {
        viewModelScope.launch {
            repository.fetchPosts(context)
        }
    }
}
