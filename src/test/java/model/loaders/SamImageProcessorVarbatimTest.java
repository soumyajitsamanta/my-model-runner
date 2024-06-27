package model.loaders;

import org.junit.jupiter.api.Test;

public class SamImageProcessorVarbatimTest {
    SamImageProcessorVarbatim ip = new SamImageProcessorVarbatim(null) ;
    
//    {
//        public ImageSize somet() {
//            // TODO Auto-generated method stub
//            ImageSize _get_preprocess_shape = _get_preprocess_shape(1024, 800, 1024);
//            return _get_preprocess_shape;
//        }
//    };
    
    @Test
    void testName() throws Exception {
        ImageSize _get_preprocess_shape = ip._get_preprocess_shape(1024, 800, 1024);
        System.err.println(_get_preprocess_shape.height+":"+_get_preprocess_shape.width);
    }
}
