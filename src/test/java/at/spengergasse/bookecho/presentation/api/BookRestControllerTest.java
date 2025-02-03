package at.spengergasse.bookecho.presentation.api;

import at.spengergasse.bookecho.FixtureFactory;
import at.spengergasse.bookecho.domain.Book;
import at.spengergasse.bookecho.service.BookService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookRestController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BookRestControllerTest {

    private @MockitoBean BookService bookService;

    private @Autowired MockMvc mockMvc;

    @Test
    void can_create_book() throws Exception{
        var book = spy(FixtureFactory.book());
        when(book.getId()).thenReturn(new Book.BookId(1L));
        when(bookService.createBook(any(),any())).thenReturn(Optional.of(book));

        var postBody = """
                {
                "title":"Determined",
                "isbn":"978-3-16-148410-0"
                }""";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "/api/books/1"))
                .andExpect(jsonPath("$.title").value("Determined"))
                .andDo(print());
    }
}