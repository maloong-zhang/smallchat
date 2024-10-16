package ae.zerotone.chat.web;

import ae.zerotone.chat.server.NettyServer;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NettyController {

  @Resource private NettyServer nettyServer;

  @RequestMapping("/index")
  public String index(Model model) {
    model.addAttribute("name", "xiaohao");
    return "index";
  }
}
