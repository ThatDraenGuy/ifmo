from swiplserver import PrologThread
import re

class QueryHandler:
    def __init__(self, pattern: str):
        self.pattern = pattern
    
    def handle_match(self, groups):
        pass

    def get_error_message(self, result) -> str:
        pass

    def get_success_message(self, result) -> str:
        pass

    def try_match(self, prolog: PrologThread, query: str):
        match = re.match(self.pattern, query, re.IGNORECASE)
        if (match is None):
            return None
        
        query = self.handle_match(match.groups())
        result = prolog.query(query)
        if not result or len(result) == 0:
            return self.get_error_message(result)
        else:
            return self.get_success_message(result)
        
        

class RecommendedBossesQueryHandler(QueryHandler):
    def __init__(self):
        super().__init__(r'What bosses are recommended to me, if I am at (.+) tier\?')

    def handle_match(self, groups):
        self.tier = groups[0]
        return f'recommended_bosses_list({self.tier}, X)'
    
    def get_success_message(self, result) -> str:
        if len(result[0]['X']) == 0:
            return self.get_error_message(result)
        return 'Bosses recommended to you are: ' + ', '.join(result[0]['X'])

    def get_error_message(self, result) -> str:
        return f'Cannot find recommended bosses for tier "{self.tier}"'


class AvailableBossesQueryHandler(QueryHandler):
    def __init__(self):
        super().__init__(r'What bosses are available to me, if I am at (.+) tier\?')

    def handle_match(self, groups):
        self.tier = groups[0]
        return f'available_bosses_list({self.tier}, X)'
    
    def get_success_message(self, result) -> str:
        if len(result[0]['X']) == 0:
            return self.get_error_message(result)
        return 'Bosses available to you are: ' + ', '.join(result[0]['X'])

    def get_error_message(self, result) -> str:
        return f'Cannot find any available bosses for tier "{self.tier}"'
    
    
class UnlockedByTierBossesQueryHandler(QueryHandler):
    def __init__(self):
        super().__init__(r'What bosses became available to me, if I just got to (.+) tier\?')

    def handle_match(self, groups):
        self.tier = groups[0]
        return f'unlocked_by_tier_bosses_list({self.tier}, X)'
    
    def get_success_message(self, result) -> str:
        if len(result[0]['X']) == 0:
            return self.get_error_message(result)
        return f'Bosses unlocked at {self.tier} tier are: ' + ', '.join(result[0]['X'])

    def get_error_message(self, result) -> str:
        return f'Cannot find any bosses that "{self.tier}" tier unlocks'