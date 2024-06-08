package umcs.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;




@RestController
@RequestMapping("rectangles")
public class RectangleController {

    private final List<Rectangle> rectangles = new ArrayList<>();

    @GetMapping("")
    public List<Rectangle> getItem() {
        return rectangles;
    }
    @GetMapping("rectangle/{index}")
    public Rectangle getItem(@PathVariable  int index){
        return rectangles.get(index);
    }
    @GetMapping("svg")
    public String getSvg() {
        StringBuilder sb = new StringBuilder();

        sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"1000\" height=\"100\" viewBox=\"0 0 500 500\" fill=\"none\">\n");
        for(int i=0; i<rectangles.size(); i++){
            sb.append("<rect width=\"" + rectangles.get(i).width() + "\" ")
                    .append(" height=\"" + rectangles.get(i).height() + "\" ")
                    .append("x=\"" + rectangles.get(i).x() + "\" y=\"" + rectangles.get(i).y() + "\" ")
                    .append("fill=\"" + rectangles.get(i).color() + "\" />\n\n");
        }
        sb.append("</svg>");

        return sb.toString();

    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody Rectangle rectangle) {
        rectangles.add(rectangle);

        return new ResponseEntity<>("Rectangle added successfully", HttpStatus.CREATED);
    }

    @PutMapping("rectangle/{index}")
    public ResponseEntity<String> updateItem(@PathVariable  int index, @RequestBody Rectangle rectangle) {
        if(index < 0 || index >= rectangles.size()){
            return new ResponseEntity<>("Index out of bounds", HttpStatus.BAD_REQUEST);
        }
        rectangles.set(index, rectangle);

        return new ResponseEntity<>("Rectangle updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("rectangle/{index}")
    public ResponseEntity<String> deleteItem(@PathVariable  int index) {
        if(index < 0 || index >= rectangles.size()){
            return new ResponseEntity<>("Index out of bounds", HttpStatus.BAD_REQUEST);
        }
        rectangles.remove(index);

        return new ResponseEntity<>("Rectangle deleted successfully", HttpStatus.OK);
    }


}

