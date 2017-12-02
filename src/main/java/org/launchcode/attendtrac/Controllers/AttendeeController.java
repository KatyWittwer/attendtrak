package org.launchcode.attendtrac.Controllers;

import org.launchcode.attendtrac.Models.Attendee;
import org.launchcode.attendtrac.Models.data.AttendeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("attendees")
public class AttendeeController {

    @Autowired
    private AttendeeDao attendeeDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Attendees");
        model.addAttribute("attendees", attendeeDao.findAll());

        return "attendee/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAttendeeForm(Model model) {
        model.addAttribute("title", "Add Attendee");
        model.addAttribute(new Attendee());
        return "attendee/add";

    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAttendeeForm(@ModelAttribute @Valid Attendee newAttendee,
                                        Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Attendee");
            return "attendee/add";
        }

        attendeeDao.save(newAttendee);
        return "attendee/index";

    }

}
