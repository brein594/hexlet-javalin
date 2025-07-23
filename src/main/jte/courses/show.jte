<!-- директивы-->
@import org.example.hexlet.dto.courses.CoursePage;
@param CoursePage page

<!-- Для наглядности мы не стали включать сюда основную структуру HTML -->
<html lang="en">
<head>
    <title>Hexlet Course</title>
</head>
<main>
    <h1>${page.getCourse().getName()}</h1>  <!--интерполяция-->
    <p>${page.getCourse().getDescription()}</p>
    <p><a href="/">Home</a></p>
</main>
</html>