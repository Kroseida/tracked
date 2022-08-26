// This is just an example,
// so you can safely delete all default props below

export default {
  search: 'Suchen',
  username: 'Nutzername',
  firstName: 'Vorname',
  lastName: 'Nachname',
  email: 'Email Adresse',
  password: 'Passwort',
  passwordRepeat: 'Passwort wiederholen',
  nextStep: 'Weiter',
  createAccount: 'Konto erstellen',
  login: 'Anmelden',
  organization: {
    id: 'ID der Organisation',
    name: 'Name der Organisation',
    description: 'Beschreibung der Organisation',
    noOrganization: 'Es sind keine Organisationen vorhanden. Erstellen Sie eine neue Organisation.',
    status: 'Status',
  },
  project: {
    id: 'ID des Projekts',
    name: 'Name des Projekts',
    description: 'Beschreibung der Projekts',
    noResults: 'Es sind keine Projekte vorhanden, die den Suchkriterien entsprechen.',
    noData: 'Es sind keine Projekte vorhanden.',
    status: 'Status',
  },
  cancel: 'Abbrechen',
  recordsPerPage: 'Einträge pro Seite',
  displayedRecords: 'Zeige Einträge {firstItem} - {lastItem} von {total}',
  active: 'Aktiviert',
  inactive: 'Deaktiviert',
  baseData: 'Stammdaten',
  projects: 'Projekte',
  activities: 'Aktivitäten',
  users: 'Benutzer',
  loading: 'Daten werden geladen...',
  menu: {
    createReport: {
      title: 'Zeitbericht erstellen',
    },
    activity: {
      title: 'Aktivitäten',
    },
    project: {
      title: 'Projekte',
    },
    organization: {
      title: 'Organisationen',
    },
    settings: {
      title: 'Einstellungen',
    },
    users: {
      title: 'Nutzer',
    },
    logout: {
      title: 'Abmelden',
    },
  },
  rules: {
    invalidUsername: 'Der Nutzername darf keine Leerzeichen enthalten.',
    invalidEmail: 'Die angegebene Email Adresse ist nicht korrekt!',
    invalidRepeatPassword: 'Die Passwörter stimmen nicht übereein.',
  },
  title: {
    createAccount: 'Erstelle ein Konto',
    login: 'Anmelden',
    organization: 'Organisationen'
  },
  action: {
    create: {
      organization: 'Organisation anlegen',
    },
    delete: {
      organization: 'Organisation löschen',
    },
    deactivate: {
      organization: 'Organisation deaktivieren',
    },
    update: {
      organization: 'Organisation speichern',
    }
  },
  text: {
    delete: {
      organization: '<br><p>Beim Löschen der Organisation werden auch alle Projekte, Aktivitäten und Zeitberichte ' +
        'innerhalb der Organisation gelöscht.</p> Möchten Sie fortfahren?',
    }
  },
  notification: {
    UNAUTHORIZED: 'Benutzername oder Passwort ist falsch.',
    ORGANIZATION_ALREADY_EXISTS: 'Es existiert bereits eine Organisation mit diesem Namen.',
    BAD_REQUEST: 'Ein Fehler in der Kommunikation mit dem Server ist aufgetreten.',
    ORGANIZATION_NOT_FOUND: 'Die Organisation konnte nicht gefunden werden.',
    NETWORK_ERROR: 'Es konnte keine Verbindung zum Server hergestellt werden.',
    organization: {
      creation: {
        success: 'Die Organisation wurde erfolgreich angelegt.',
      },
      deletion: {
        success: 'Die Organisation wurde erfolgreich gelöscht.',
      },
      update: {
        success: 'Die Organisation wurde erfolgreich aktualisiert.',
      }
    },
    authentication: {
      authenticate: {
        success: 'Sie wurden erfolgreich angemeldet.',
      }
    }
  }

};
