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
    description: 'Beschreibung des Projekts',
    noResults: 'Es sind keine Projekte vorhanden, die den Suchkriterien entsprechen.',
    noData: 'Es sind keine Projekte vorhanden. Erstellen Sie ein neues Projekt.',
    status: 'Status',
    startDate: 'Startdatum',
    endDate: 'Enddatum',
  },
  activity: {
    id: 'ID der Aktivität',
    name: 'Name der Aktivität',
    description: 'Beschreibung der Aktivität',
    noResults: 'Es sind keine Aktivitäten vorhanden, die den Suchkriterien entsprechen.',
    noData: 'Es sind keine Aktivitäten vorhanden. Erstellen Sie eine neue Aktivität.',
    status: 'Status',
  },
  cancel: 'Abbrechen',
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
      title: 'Benutzer',
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
      project: 'Projekt anlegen',
      activity: 'Aktivität anlegen',
    },
    delete: {
      organization: 'Organisation löschen',
      project: 'Projekt löschen',
      activity: 'Aktivität löschen',
    },
    deactivate: {
      organization: 'Organisation deaktivieren',
    },
    update: {
      organization: 'Organisation speichern',
      project: 'Projekt speichern',
      activity: 'Aktivität speichern',
    },
  },
  text: {
    delete: {
      organization: '<br><p>Beim Löschen der Organisation werden auch alle Projekte, Aktivitäten und Zeitberichte ' +
        'innerhalb der Organisation gelöscht.</p> Möchten Sie fortfahren?',
      project: '<br><p>Beim Löschen des Projekts werden auch alle Zeitberichte innerhalb des Projekts gelöscht.</p> ' +
        'Möchten Sie fortfahren?',
      activity: '<br><p>Beim Löschen der Aktivität werden auch alle Zeitberichte innerhalb der Aktivität gelöscht.</p> ' +
        'Möchten Sie fortfahren?',
    }
  },
  notification: {
    UNAUTHORIZED: 'Benutzername oder Passwort ist falsch.',
    ORGANIZATION_ALREADY_EXISTS: 'Es existiert bereits eine Organisation mit diesem Namen.',
    PROJECT_ALREADY_EXISTS: 'Es existiert bereits ein Projekt mit diesem Namen.',
    ACTIVITY_ALREADY_EXISTS: 'Es existiert bereits eine Aktivität mit diesem Namen.',
    BAD_REQUEST: 'Ein Fehler in der Kommunikation mit dem Server ist aufgetreten.',
    ORGANIZATION_NOT_FOUND: 'Die Organisation konnte nicht gefunden werden.',
    PROJECT_NOT_FOUND: 'Das Projekt konnte nicht gefunden werden.',
    ACTIVITY_NOT_FOUND: 'Die Aktivität konnte nicht gefunden werden.',
    NETWORK_ERROR: 'Es konnte keine Verbindung zum Server hergestellt werden.',
    INTERNAL_ERROR: 'Ein interner Serverfehler ist aufgetreten.',
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
    project: {
      creation: {
        success: 'Das Projekt wurde erfolgreich angelegt.',
      },
      deletion: {
        success: 'Das Projekt wurde erfolgreich gelöscht.',
      },
      update: {
        success: 'Das Projekt wurde erfolgreich aktualisiert.',
      }
    },
    activity: {
      creation: {
        success: 'Die Aktivität wurde erfolgreich angelegt.',
      },
      deletion: {
        success: 'Die Aktivität wurde erfolgreich gelöscht.',
      },
      update: {
        success: 'Die Aktivität wurde erfolgreich aktualisiert.',
      }
    },
    authentication: {
      authenticate: {
        success: 'Sie wurden erfolgreich angemeldet.',
      }
    }
  }

};
