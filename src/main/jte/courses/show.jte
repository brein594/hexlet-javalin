@import org.example.hexlet.dto.courses.CoursePage; //директивы
@param CoursePage page

<!-- Для наглядности мы не стали включать сюда основную структуру HTML -->
<main>
    <h1>${page.getCourse().getName()}</h1>  //интерполяция
    <p>${page.getCourse().getDescription()}</p>
</main