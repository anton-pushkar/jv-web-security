package taxi.controller.car;

import java.io.IOException;
import java.util.List;
import taxi.lib.Injector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.model.Car;
import taxi.service.CarService;

@WebServlet(urlPatterns = "/drivers/cars")
public class GetMyCurrentCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute("driver_id");
        List<Car> allByDriver = carService.getAllByDriver(id);
        req.setAttribute("cars", allByDriver);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
