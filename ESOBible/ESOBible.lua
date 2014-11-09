ESOBible = {}
ESOBible.name = "ESOBible"

function ESOBible:Initialize()
  book = books[math.random(table.getn(books)-1)]
  startIndex = 1
  EVENT_MANAGER:RegisterForEvent(self.name, EVENT_SHOW_BOOK, self.OnBookShow)
  EVENT_MANAGER:RegisterForEvent(self.name, EVENT_HIDE_BOOK, self.OnBookHide)
end

function ESOBible:OnBookShow(eventCode, bookTitle, body, medium, showTitle)
  d(verses[book][startIndex])
  startIndex = startIndex + 1
end

function ESOBible:OnBookHide(eventCode)
end

function ESOBible.OnAddOnLoaded(event, addonName)
  if addonName == ESOBible.name then
    ESOBible:Initialize()
  end
end

EVENT_MANAGER:RegisterForEvent(ESOBible.name, EVENT_ADD_ON_LOADED, ESOBible.OnAddOnLoaded)
