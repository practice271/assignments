package patterns

import java.util.*

public abstract class Page { }

class SkillsRage       : Page() { }

class EducationPage    : Page() { }

class ExperiencePage   : Page() { }

class IntroductionPage : Page() { }

class ResultsPage      : Page() { }

class ConclusionPage   : Page() { }

class SummaryPage      : Page() { }

class BibliographyPage : Page() { }

abstract class Document
{
    private var pages = LinkedList<Page>()

    public var Pages: LinkedList<Page> = pages
        get() = $Pages

    public fun Document() {
        CreatePages()
    }
    // Factory Method
    public abstract fun CreatePages()
}
class Resume : Document()
{

    // Factory Method implementation
    public override fun CreatePages()
    {
        Pages.add(SkillsRage())
        Pages.add(EducationPage())
        Pages.add(ExperiencePage())
    }
}

class Report : Document()
{
    // Factory Method implementation
    public override fun CreatePages()
    {
        Pages.add(IntroductionPage())
        Pages.add(ResultsPage())
        Pages.add(ConclusionPage())
        Pages.add(SummaryPage())
        Pages.add(BibliographyPage())
    }
}

