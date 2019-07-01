package editor.visual

import editor.backend.*
import editor.*
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

/**
 * Created by Dima on 22-Aug-15.
 * Updated by FiqiDev on 29-Juni-2019
 *
 */

fun buildMenuBar(): JMenuBar {


    //setup icon - about
    val iconAboutApplication = ImageIcon("icons/about.png")

    //setup menu - about
    val aboutMenu = JMenu("About")
    val aboutApplication = JMenuItem("About Application", iconAboutApplication)

    aboutMenu.add(aboutApplication)


    //About actionListener
    aboutApplication.addActionListener {
        var contentText: String? = null
        val text: JLabel = JLabel()

        val panelAboutApplication = JPanel(FlowLayout())
        panelAboutApplication.border = BorderFactory.createEmptyBorder(8, 8, 8, 8)

        val aboutApplicationFrame = JFrame()
        aboutApplicationFrame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                aboutApplicationFrame.dispose()
            }
        })

        aboutApplicationFrame.setSize(500, 300)
        aboutApplicationFrame.setLocationRelativeTo(frame)

        aboutApplicationFrame.title = "About this Application - ${frame.title}"

        contentText = "<html><body><p>" +
                "Name: " + frame.title + "<br />" +
                "Version: 1.0" + "<br />" +
                "Developer: FiqiDev" +
                "</p></body></html>"

        text.text = contentText
        panelAboutApplication.add(text)
        aboutApplicationFrame.add(panelAboutApplication)

        aboutApplicationFrame.isVisible = true

    }


    val bar = JMenuBar()
    bar.add(setupMenu("File", mapOf("New" to ::newFile, "Open" to ::openFile, "Save" to ::saveFile)))
    bar.add(setupMenu("Build", mapOf("Build & Restart" to ::buildRestart)))
    bar.add(setupMenu("Syntax", mapOf("Java" to ::javaSyntax, "Kotlin" to ::kotlinSyntax)))
    bar.add(aboutMenu)
    return bar
}



private fun setupMenu(name: String, map: Map<String, () -> Unit>): JMenu {
    val menu = JMenu(name)
    for (e in map)
        menu.add(setupButton(e.key, e.value))
    return menu
}

private fun setupButton(name: String, func: () -> Unit): JMenuItem {
    val button = JMenuItem(name)
    button.preferredSize = Dimension(200, 25)
    button.addActionListener({ e: ActionEvent ->
        run {
            if (e.source == button) {
                func()
            }
        }
    })
    return button
}